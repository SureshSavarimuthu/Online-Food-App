import React from "react";
import Logo from "./Logo/Logo";
import Home from "./Home/Home";

const NavBar = () => {
  return (
    <div>
      <div className="Navbar">
        <Logo />
        <Home />
      </div>
    </div>
  );
};

export default NavBar;
