import React from "react";
import ModuleCard from "./ModuleCard";
import { modulesData } from "./ModulesData";
import ButtonAppBar from "./Appbar";


const HomePage = () => {
    return (
        <>
        <ButtonAppBar/>
        <h1>Available Modules</h1>
      
      <div className="module-container">
      {modulesData.map(module => (
          <ModuleCard key={module.id} title={module.name} image={module.image} />
        ))}
      
      {/* <div className="module-card">
        <Col md={6}>
          <Card className="module-card">
            <Card.Img variant="top" src="workflow_image.jpg" />
            <Card.Body>
              
              <Button href="/workflow" variant="primary"><Card.Title>Workflow Manager</Card.Title></Button>
            </Card.Body>
          </Card>
        </Col>
        </div>
        <div className="module-card">
        <Col md={6}>
          <Card className="module-card">
            <Card.Img variant="top" src="security_image.jpg"/>
            <Card.Body>
              
              <Button href="/security" variant="primary"><Card.Title>Security</Card.Title></Button>
            </Card.Body>
          </Card>
        </Col>
        </div>*/}
        </div> 
        </>
    )
};

export default HomePage;