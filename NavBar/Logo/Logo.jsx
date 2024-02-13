import React from "react";
import img from "./logo.png";

const Logo = () => {
  return (
    <div>
      <div>
        <img
          src={img}
          alt=""
          style={{ width: "60px", height: "60px", borderRadius: "50%" }}
        />
      </div>
    </div>
  );
};

export default Logo;
