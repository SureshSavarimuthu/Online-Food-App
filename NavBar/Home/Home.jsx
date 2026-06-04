import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";

const Home = () => {
  const [userData, setUserData] = useState([]);
  // const navigate = useNavigate();

  return (
    <div>
      <div className="Home">
        <Link className="a" to="">
          Home
        </Link>
        <Link className="a" to="">
          Menu
        </Link>
        <Link className="a" to="">
          About
        </Link>
        <Link className="a" href="#" to="/user/saveUser">
          Login
        </Link>
        <Link className="a" to="">
          Career
        </Link>
      </div>
    </div>
  );
};

export default Home;
