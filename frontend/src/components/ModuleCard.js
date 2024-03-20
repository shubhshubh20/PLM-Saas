import React from 'react'
import PropTypes from 'prop-types'
import { Col, Card, Button } from 'react-bootstrap';

function ModuleCard(props) {
  return (
    <div className="module-card">
        <Col md={6}>
          <Card className="module-card">
            <Card.Img variant="top" src={props.image} />
            <Card.Body>
              
              {/* <Card.Text>
                Description of Create Workflow module.
              </Card.Text> */}
              <Button href="/workflow" variant="primary"><Card.Title>{props.title}</Card.Title></Button>
            </Card.Body>
          </Card>
        </Col>
        </div>
  )
}

ModuleCard.propTypes = {
    title: PropTypes.string.isRequired,
    image: PropTypes.string.isRequired
}

export default ModuleCard

