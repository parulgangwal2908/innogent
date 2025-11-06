import Button from "react-bootstrap/Button";
import Container from "react-bootstrap/Container";
import NavLink from "react-bootstrap/esm/NavLink";
import Form from "react-bootstrap/Form";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import { IoIosNotifications } from "react-icons/io";
import { IoCart } from "react-icons/io5";
import { CgProfile } from "react-icons/cg";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

function Header({ onSearch }) {
  const navigate = useNavigate();
  const handleHome = () => {
    navigate("/");
  };
  return (
    <Navbar expand="lg" className="bg-body-tertiary">
      <Container fluid className="ms-4 me-4 m-2">
        <Navbar.Brand href="#" className="ms-3">
          Logo
        </Navbar.Brand>

        <Nav className="me-auto my-2 my-lg-0" style={{ maxHeight: "100px" }}>
          <Nav.Link href="#" onClick={handleHome}>
            Home
          </Nav.Link>
        </Nav>
        <Form className="d-flex mx-auto" style={{ width: "40%" }}>
          <Form.Control
            type="search"
            placeholder="Search"
            className=" me-2"
            aria-label="Search"
            onChange={(e) => onSearch(e.target.value)}
          />
          <Button variant="outline-secondary">Search</Button>
        </Form>
        <NavLink href="#notifications" className="ms-5">
          <IoIosNotifications size={33} color="#282823ab" />
        </NavLink>
        <NavLink href="#profile" className="ms-4">
          <CgProfile size={33} color="#282823ab" />
        </NavLink>
        <NavLink href="#cart" className="ms-4 me-4">
          <IoCart size={33} color="#282823ab" />
        </NavLink>
      </Container>
    </Navbar>
  );
}

export default Header;
