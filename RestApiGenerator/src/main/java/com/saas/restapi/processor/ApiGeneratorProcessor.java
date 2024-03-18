package com.saas.restapi.processor;

import com.saas.restapi.annotation.ApiGenerator;
import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleTypeVisitor9;
import javax.persistence.Id;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AutoService(Processor.class)
@SupportedAnnotationTypes("com.saas.restapi.annotation.ApiGenerator")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class ApiGeneratorProcessor extends AbstractProcessor {


    private String className;
    private String packageName;

    // TODO: Some error coming while using this processor create a new processor from scratch and give basic code with some printing statements

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        boolean isClaimed = false;
        Set<? extends Element> apiGeneratedAnnotatedClasses = roundEnv.getElementsAnnotatedWith(ApiGenerator.class);
        for (Element element : apiGeneratedAnnotatedClasses) {
            if (element.getKind() == ElementKind.CLASS) {
                className = element.getSimpleName().toString();
                packageName = processingEnv.getElementUtils().getPackageOf(element).toString();
                generateServiceInterface(element);
                generateRepositoryInterface(element);
                generateServiceClass(element);
                generateControllerClass(element);
            }
        }
        return false;
    }

    private void generateControllerClass(Element element){
        StringBuilder controllerClassBuilder = new StringBuilder();
        controllerClassBuilder.append("package ")
                .append(getParentPackageName(this.packageName) + ".controller" + ";\n\n")
                .append("import " + getParentPackageName(this.packageName) + ".service." + this.className + "Service;\n")
                .append("import " + this.packageName + "." + this.className + ";\n")
                .append("import org.springframework.beans.factory.annotation.Autowired;\n" +
                        "import org.springframework.web.bind.annotation.*;\n\n" +
                        "import java.util.List;\n\n" +
                        "@RestController\n" +
                        "@RequestMapping(\"/" + this.className.toLowerCase() + "\")\n")
                .append("public class " + this.className + "Controller {\n\n")
                .append("\t@Autowired\n" +
                        "\tprivate " + this.className + "Service " + this.className.toLowerCase() + "Service;\n\n")
                .append("\t@GetMapping(\"/get/{"+ getIdFieldName(element) + "}\")\n")
                .append("\tpublic " + this.className + " get" + this.className + "(@PathVariable " + getIdFieldType(element) + " " + getIdFieldName(element) + ") {\n")
                .append("\t\treturn " + this.className.toLowerCase() + "Service.get" + this.className + "(" + getIdFieldName(element) + ");\n\t}\n\n")
                .append("\t@GetMapping(\"/getAll\")\n" +
                        "\tpublic List<" + this.className +"> getAll" + this.className +"s(){\n" +
                        "\t\treturn " +this.className.toLowerCase() + "Service.getAll" + this.className + "s();\n" +
                        "\t}\n\n")
                .append("\t@PostMapping\n" +
                        "\tpublic " + this.className + " save" + this.className + "(@RequestBody " + this.className
                        + " " + this.className.toLowerCase() + "){\n" +
                        "\t\treturn " + this.className.toLowerCase() + "Service.save" + this.className + "(" +
                        this.className.toLowerCase()+ ");\n" +
                        "\t}\n\n")
                .append("\t@DeleteMapping(\"{" + getIdFieldName(element) + "}\")\n" +
                        "\tpublic " + this.className + " delete" + this.className + "(@PathVariable " +
                        getIdFieldType(element) + " " + getIdFieldName(element) + "){\n" +
                        "\t\treturn " + this.className.toLowerCase() +"Service.delete" + this.className + "(" +
                        this.className.toLowerCase() + "Service.get" + this.className + "(" + getIdFieldName(element) + "));\n" +
                        "\t}\n" +
                        "}");

        try {
            Writer writer = processingEnv.getFiler()
                    .createSourceFile(getParentPackageName(this.packageName) + ".controller" + "." + this.className + "Controller")
                    .openWriter();
            writer.write(controllerClassBuilder.toString());
            writer.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    private void generateServiceInterface(Element element){
        StringBuilder serviceInterfaceBuilder = new StringBuilder();
        serviceInterfaceBuilder.append("package ")
                .append(getParentPackageName(this.packageName) + ".service" + ";\n\n")
                .append("import " + this.packageName + "." + this.className + ";\n")
                .append("import java.util.List;\n\n")
                .append("public interface " + this.className + "Service{\n\n")
                .append("\tpublic List<" + this.className + "> getAll" + this.className + "s();\n\n")
                .append("\tpublic " + this.className + " save" + this.className + "(" + this.className + " " + this.className.toLowerCase() + ");\n\n")
                .append("\tpublic " + this.className + " get" + this.className + "(" + getIdFieldType(element) + " " + getIdFieldName(element) + ");\n\n")
                .append("\tpublic " + this.className + " delete" + this.className + "(" + this.className + " " + this.className.toLowerCase() + ");\n\n")
                .append("}");
        try {
            Writer writer = processingEnv.getFiler()
                    .createSourceFile(getParentPackageName(this.packageName) + ".service" + "." + this.className + "Service")
                    .openWriter();
            writer.write(serviceInterfaceBuilder.toString());
            writer.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }


    private void generateRepositoryInterface(Element element){
        StringBuilder repositoryBuilder = new StringBuilder();
        repositoryBuilder.append("package ")
                .append(getParentPackageName(this.packageName) + ".repository" + ";\n\n")
                .append("import " + this.packageName + "." + this.className + ";\n")
                .append("import org.springframework.data.jpa.repository.JpaRepository;\n")
                .append("import org.springframework.stereotype.Repository;\n\n")
                .append("@Repository\n")
                .append("public interface " + this.className + "Repository extends JpaRepository<" + this.className + "," +
                        getIdFieldType(element) + ">{\n}");



        try {
            Writer writer = processingEnv.getFiler()
                    .createSourceFile(getParentPackageName(this.packageName) + ".repository" + "." + this.className + "Repository")
                    .openWriter();
            writer.write(repositoryBuilder.toString());
            writer.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void generateServiceClass(Element element){
        StringBuilder serviceClassBuilder = new StringBuilder();
        serviceClassBuilder.append("package ")
                .append(getParentPackageName(this.packageName) + ".service" + ";\n\n")
                .append("import " + this.packageName + "." + this.className + ";\n")
                .append("import org.springframework.stereotype.Service;\n\n")
                .append("import " + getParentPackageName(this.packageName) + ".repository." + this.className + "Repository;\n")
                .append("import " + getParentPackageName(this.packageName) + ".service." + this.className + "Service;\n")
                .append("import org.springframework.beans.factory.annotation.Autowired;\n\n")
                .append("import java.util.List;\n\n")
                .append("@Service\n")
                .append("public class " + this.className + "ServiceImpl implements " + this.className + "Service {\n\n")
                .append("\t@Autowired\n")
                .append("\tprivate " + this.className + "Repository " + this.className.toLowerCase() + "Repository;\n\n")
                .append("\t@Override\n")
                .append("\tpublic List<" + this.className + "> getAll" + this.className + "s() {\n\n")
                .append("\t\treturn " + this.className.toLowerCase() +"Repository.findAll();\n\t}\n\n")
                .append("\t@Override\n")
                .append("\tpublic " + this.className + " save" + this.className + "(" + this.className + " " + this.className.toLowerCase() + "){\n\n")
                .append("\t\treturn " + this.className.toLowerCase() + "Repository.save(" + this.className.toLowerCase() + ");\n\t}\n\n")
                .append("\t@Override\n")
                .append("\tpublic " + this.className + " get" + this.className + "(" + getIdFieldType(element) + " " + getIdFieldName(element) + "){\n\n")
                .append("\t\treturn " + this.className.toLowerCase() + "Repository.getById(" + getIdFieldName(element) + ");\n\t}\n\n")
                .append("\t@Override\n")
                .append("\tpublic " + this.className + " delete"  + this.className + "(" + this.className + " " + this.className.toLowerCase() + "){\n\n")
                .append("\t\t" + this.className.toLowerCase() + "Repository.delete(" + this.className.toLowerCase() + ");\n")
                .append("\t\treturn " + this.className.toLowerCase() + ";\n")
                .append("\t}\n\n")
                .append("}");
        try {
            Writer writer = processingEnv.getFiler()
                    .createSourceFile(getParentPackageName(this.packageName) + ".service" + "." + this.className + "ServiceImpl")
                    .openWriter();
            writer.write(serviceClassBuilder.toString());
            writer.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

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
                        .anyMatch(a -> a.getAnnotationType().toString().equals("jakarta.persistence.Id")))
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
            return switch (fieldType.getKind()) {
                case BOOLEAN -> Boolean.class.getCanonicalName();
                case BYTE -> Byte.class.getCanonicalName();
                case SHORT -> Short.class.getCanonicalName();
                case INT -> Integer.class.getCanonicalName();
                case LONG -> Long.class.getCanonicalName();
                case CHAR -> Character.class.getCanonicalName();
                case FLOAT -> Float.class.getCanonicalName();
                case DOUBLE -> Double.class.getCanonicalName();
                default -> fieldType.toString();
            };
        } else {
            return String.class.getCanonicalName(); // Default to String if id field is not found
        }
    }


}
