import Table from "react-bootstrap/Table";
import Form from "react-bootstrap/Form";
import InputGroup from "react-bootstrap/InputGroup";
import Cards from "./Cards.jsx";
import { useEffect, useState } from "react";

import React from "react";

function Category_Filter({ products }) {
  const [categories, setCategories] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState("All");
  useEffect(() => {
    if (products.length > 0) {
      const categoryCounts = {};
      products.forEach((product) => {
        categoryCounts[product.category] =
          (categoryCounts[product.category] || 0) + 1;
      });
      const formattedCategories = Object.entries(categoryCounts).map(
        ([category, count]) => ({ category, count })
      );
      setCategories(formattedCategories);
    }
  }, [products]);

  const filteredProducts =
    selectedCategory === "All"
      ? products
      : products.filter((product) => product.category === selectedCategory);

  return (
    <>
      <div className="d-flex">
        <div className="d-flex flex-column ">
          <div
            className=" d-flex flex-column m-3 p-3"
            style={{ width: "280px", border: "1px solid #ddd" }}
          >
            <div className="text-dark fw-bold mb-3">
              <h5>Product Type</h5>
            </div>
            <div className="d-flex justify-content-center align-items-centers">
              <Table className="mb-2">
                <tr
                  className="m-1"
                  onClick={() => setSelectedCategory("All")}
                  style={{ cursor: "pointer" }}
                >
                  <td
                    className={` fs-6 p-1 p-1 ${
                      selectedCategory === "All"
                        ? "fw-bold text-dark-emphasis "
                        : "text-dark"
                    }`}
                  >
                    {" "}
                    All
                  </td>
                  <td className="text-end m-1 p-1">
                    <span
                      className="text-secondary p-1 ps-2 pe-2"
                      style={{
                        backgroundColor: "#9b9b922d",
                        borderRadius: "10px",
                        fontSize: "12px",
                      }}
                    >
                      {products.length}+
                    </span>
                  </td>
                </tr>
                {categories.map((name) => (
                  <tr
                    className="m-1 "
                    key={name.category}
                    onClick={() => setSelectedCategory(name.category)}
                    style={{ cursor: "pointer" }}
                  >
                    <td
                      className={`fs-6 p-1 ${
                        selectedCategory === name.category
                          ? "fw-bold text-dark-emphasis"
                          : "text-dark"
                      }`}
                    >
                      {" "}
                      {name.category}
                    </td>
                    <td className="text-end p-1">
                      <span
                        className="text-secondary p-1 ps-2 pe-2"
                        style={{
                          backgroundColor: "#9b9b922d",
                          borderRadius: "10px",
                          fontSize: "12px",
                        }}
                      >
                        {name.count}+
                      </span>
                    </td>
                  </tr>
                ))}
              </Table>
            </div>
          </div>
          {/* <div
            className="d-flex flex-column m-3 p-3"
            style={{ width: "280px", border: "1px solid #ddd" }}
          > */}
          {/* <div className="text-dark fw-bold mb-3">
              <h5>Rating</h5>
            </div> */}
          {/* <div className="d-flex justify-content-center align-items-centers"></div> */}
          {/* <InputGroup className="mb-3">
              <InputGroup.Checkbox aria-label="Checkbox for following text input" />
              <span>1-2</span>
            </InputGroup> */}
          {/* <InputGroup className="mb-3">
              <InputGroup.Checkbox aria-label="Checkbox for following text input" />
              <span>1-2</span>
            </InputGroup> */}
          {/* <InputGroup className="mb-3">
              <InputGroup.Checkbox aria-label="Checkbox for following text input" />
              <span>1-2</span>
            </InputGroup> */}
          {/* <InputGroup className="mb-3">
              <InputGroup.Checkbox aria-label="Checkbox for following text input" />
              <span>1-2</span>
            </InputGroup> */}
          {/* <InputGroup className="mb-3">
              <InputGroup.Checkbox aria-label="Checkbox for following text input" />
              <span>1-2</span>
            </InputGroup> */}
          {/* </div> */}
        </div>
        <div className=" m-2 d-flex flex-wrap gap-4">
          {filteredProducts.length > 0 ? (
            filteredProducts.map((product) => (
              <Cards products={product} key={product.id}></Cards>
            ))
          ) : (
            <p className="text-muted ms-4 mt-4">No products found.</p>
          )}
        </div>
      </div>
    </>
  );
}

export default Category_Filter;
