import React from "react";
import { useLocation, useNavigate } from "react-router-dom";

function CardDetail() {
  const location = useLocation();
  const { product } = location.state || {};

  if (!product) {
    return <div>No product data available.</div>;
  }
  return (
    <>
      <div className="container mt-4">
        <div className="row">
          <div className="col-md-6 d-flex justify-content-center align-items-center">
            <img
              src={product.image}
              alt={product.title}
              style={{
                maxWidth: "100%",
                maxHeight: "400px",
                objectFit: "contain",
                backgroundColor: "#f8f9fa32",
                padding: "20px",
                border: "1px solid #ddd",
              }}
            />
          </div>
          <div className="col-md-6">
            <h2 className="mb-2  fw-bold fs-1">{product.title}</h2>
            <p className="mb-2 fs-5 text-secondary">
              <span className="fw-bold" style={{ color: "#FFD93D" }}>
                {product.rating.rate}{" "}
              </span>
              rating from{" "}
              <span className="fw-bold" style={{ color: "#4ec463ff" }}>
                {product.rating.count} users
              </span>
            </p>
            <h3 className="text-dark fw-bold  mb-3">${product.price}</h3>
            <p className="mb-4">Category: {product.category}</p>
            <p className="mb-4 text-secondary ">{product.description}</p>

            <button className="btn btn-success">Add to Cart</button>
          </div>
        </div>
      </div>
    </>
  );
}

export default CardDetail;
