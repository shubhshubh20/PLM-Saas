package com.saas.plm.processor;

import com.saas.plm.annotation.ApiGenerator;
import com.saas.plm.annotation.Persisted;
import com.saas.plm.annotation.UniqueKey;
import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AutoService(Processor.class)
@SupportedAnnotationTypes("com.saas.plm.annotation.ApiGenerator")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class ApiGeneratorProcessor extends AbstractProcessor {

    private static final List<String> EXCEPTION_CLASS_NAMES = new ArrayList<>() {{
        add("RequestExceptionType2");
        add("ServiceExceptionType2");
    }};

    private String className;
    private String packageName;

    // TODO: Some error coming while using this processor create a new processor from scratch and give basic code with some printing statements

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
//        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Processing element: ");
//        Set<? extends Element> apiGeneratedElements = roundEnv.getElementsAnnotatedWith(ApiGenerator.class);
//        for(Element element: apiGeneratedElements){
//            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Processing element: ");
////            className = element.getSimpleName().toString();
////            packageName = processingEnv.getElementUtils().getPackageOf(element).toString();
////            System.out.println("Printing something");
////            generateControllerClass(element);
////            generateRepositoryClass(element);
////            generateServiceClass(element);
//            generateServiceInterface(element);
//            generateExceptionClasses();

        boolean isClaimed = false;
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Processing element: ");
        Set<? extends Element> fileDbGeneratedAnnotatedClasses = roundEnv.getElementsAnnotatedWith(ApiGenerator.class);
        for (Element element : fileDbGeneratedAnnotatedClasses) {
            if (element.getKind() == ElementKind.CLASS) {
                TypeElement classElement = (TypeElement) element;

                List<VariableElement> fields = new ArrayList<>();
                List<VariableElement> uniqueKeyFields = new ArrayList<>();
                for (Element enclosedElement : classElement.getEnclosedElements()) {
                    if (enclosedElement.getKind() == ElementKind.FIELD
                            && enclosedElement.getAnnotation(UniqueKey.class) != null) {
                        VariableElement fieldElement = (VariableElement) enclosedElement;
                        uniqueKeyFields.add(fieldElement);
                    } else if (enclosedElement.getKind() == ElementKind.FIELD
                            && enclosedElement.getAnnotation(Persisted.class) != null) {
                        VariableElement fieldElement = (VariableElement) enclosedElement;
                        fields.add(fieldElement);
                    }
                }

                if (!fields.isEmpty()) {
                    TypeElement enclosingClass = (TypeElement) fields.stream().findAny().get().getEnclosingElement();
                    this.packageName = processingEnv.getElementUtils().getPackageOf(enclosingClass).toString();
                    this.className = enclosingClass.getSimpleName().toString();
                    generateExceptionClasses();
//                    generateServiceInterface(element);
                }
            }
        }
        return false;
    }

    private void generateControllerClass(Element element){
        //This method will create controller class based on the class on which the annotation is called
        List<? extends Element> enclosedElements = element.getEnclosedElements();
        StringBuilder controllerClass = new StringBuilder();

    }

    private void generateServiceInterface(Element element){
        StringBuilder serviceInterfaceBuilder = new StringBuilder();
        this.packageName = getParentPackageName(this.packageName) + ".services";
        serviceInterfaceBuilder.append("package ")
                .append(this.packageName + ";\n\n")
                .append("import " + this.packageName + "." + this.className + ";\n")
                .append("import java.util.List;\n\n")
                .append("public interface " + this.className + "Service{\n\n")
                .append("\tList<" + this.className + "> getAll" + this.className + "s();\n\n")
                .append("\tvoid save" + this.className + "(" + this.className + " " + this.className.toLowerCase() + ");\n\n")
                .append("\t" + this.className + " get" + this.className + "(" + getIdFieldType(element) + " " + getIdFieldName(element) + ");\n\n")
                .append("\tvoid delete" + this.className + "(" + this.className + " " + this.className.toLowerCase() + ");\n\n")
                .append("}");
        try {
            Writer writer = processingEnv.getFiler()
                    .createSourceFile(this.packageName + "." + this.className + "Service")
                    .openWriter();
            writer.write(serviceInterfaceBuilder.toString());
            writer.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void generateExceptionClasses() {
        for (String exceptionClassName : EXCEPTION_CLASS_NAMES) {
            StringBuilder body = new StringBuilder();
            body.append("package ").append(this.packageName).append(";\n\n");
            body.append("public class ").append(this.className).append(exceptionClassName).append(" extends Exception {\n");
            body.append("   private String message;\n\n");
            body.append("   public ").append(this.className).append(exceptionClassName).append("(String message) {\n");
            body.append("       this.message = message;\n");
            body.append("   }\n\n");
            body.append("   public String getMessage() {\n");
            body.append("       return this.message;\n");
            body.append("   }\n\n");
            body.append("   public String toString() {\n");
            body.append("       return this.getMessage();\n");
            body.append("   }\n");
            body.append("}\n");

            try {
                Writer writer = processingEnv.getFiler()
                        .createSourceFile(this.packageName + "." + this.className + exceptionClassName)
                        .openWriter();
                writer.write(body.toString());
                writer.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private void generateRepositoryClass(Element element){}

    private void generateServiceClass(Element element){}

    private String getParentPackageName(String packageName) {
        int lastIndex = packageName.lastIndexOf('.');
        if (lastIndex != -1) {
            return packageName.substring(0, lastIndex);
        } else {
            return ""; // Root package has no parent
        }
    }

    // Method to find the field with @id annotation and return its name
    private String getIdFieldName(Element userElement) {
        List<? extends Element> fields = userElement.getEnclosedElements().stream()
                .filter(e -> e.getKind() == ElementKind.FIELD)
                .filter(e -> e.getAnnotationMirrors().stream()
                        .anyMatch(a -> a.getAnnotationType().toString().equals("javax.persistence.Id")))
                .collect(Collectors.toList());

        if (!fields.isEmpty()) {
            return fields.get(0).getSimpleName().toString();
        }

        return "id"; // Default if no @Id annotation found
    }

    // Method to find the type of the id field
    private String getIdFieldType(Element userElement) {
        VariableElement idField = null;
        for (Element element : userElement.getEnclosedElements()) {
            if (element.getKind() == ElementKind.FIELD && element.getSimpleName().toString().equals(getIdFieldName(userElement))) {
                idField = (VariableElement) element;
                break;
            }
        }
        if (idField != null) {
            TypeMirror fieldType = idField.asType();
            return fieldType.toString();
        } else {
            return "String"; // Default to String if id field is not found
        }
    }

}
