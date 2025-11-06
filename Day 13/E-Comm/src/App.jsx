import Category_Filter from "./components/Category_Filter";
import Header from "./components/Header";
import axios from "axios";
import CardDetail from "./components/CardDetail";
import React, { useState, useEffect } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

function App() {
  const [products, setProducts] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const response = await axios.get("https://fakestoreapi.com/products");
        setProducts(response.data);
      } catch (error) {
        console.error("Error fetching products:", error);
      }
    };

    fetchProducts();
  }, []);

  console.log(products);

  const filteredProducts = products.filter((product) =>
    product.title.toLowerCase().includes(searchTerm.toLowerCase())
  );
  console.log(filteredProducts);

  return (
    <>
      <Router>
        <Header onSearch={setSearchTerm} />
        <Routes>
          <Route
            path="/"
            element={<Category_Filter products={filteredProducts} />}
          />
          <Route path="/product/:id" element={<CardDetail />} />
        </Routes>
      </Router>
    </>
  );
}

export default App;
