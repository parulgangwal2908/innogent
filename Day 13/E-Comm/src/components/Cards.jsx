import React from "react";
import Button from "react-bootstrap/Button";
import Card from "react-bootstrap/Card";
import { useNavigate } from "react-router-dom";

function Cards({ products }) {
  const navigate = useNavigate();
  const handleClick = () => {
    navigate(`/product/${products.id}`, { state: { product: products } });
  };
  return (
    <>
      <div className="d-flex flex-wrap m-2 ">
        <div className=" d-flex justify-content-center">
          <Card
            onClick={handleClick}
            style={{
              cursor: "pointer",
              width: "16rem",
              height: "330px",
              display: "flex",
              flexDirection: "column",
              justifyContent: "space-between",
            }}
          >
            <Card.Img
              variant="top"
              src={products.image}
              style={{
                height: "250px",
                objectFit: "contain",
                padding: "10px",
                backgroundColor: "#a0878713",
                borderBottom: "1px solid #ddd",
              }}
            />
            <Card.Body>
              <Card.Title
                className="text-secondary fs-6"
                style={{
                  whiteSpace: "nowrap",
                  overflow: "hidden",
                  textOverflow: "ellipsis",
                }}
              >
                {products.title}
              </Card.Title>
              <Card.Text className="fw-bold  ">${products.price}</Card.Text>
            </Card.Body>
          </Card>
        </div>
      </div>
    </>
  );
}

export default Cards;
