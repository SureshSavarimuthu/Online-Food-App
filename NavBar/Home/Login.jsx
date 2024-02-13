import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import axios from "axios";

const Login = () => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [password, setPassword] = useState("");
  const [role, setRole] = useState("ADMIN");
  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    let payload = { name, email, phoneNumber, password, role };
    console.log(payload);

    axios
      .post("http://localhost:8080/user/saveUser", payload)

      .then((response) => {
        console.log("saved successfull");
        console.log(response.data); // Handle the response data
      })

      .catch((error) => {
        console.log("ERROR ");
        console.error("Error:", error); // Handle any errors
      });
  };
  return (
    <div>
      {/* {" "} */}
      <div className="loginpage">
        <form action="" className="container">
          <div className="card">
            <div className="card-title" style={{ textAlign: "center" }}>
              <h2>User Create</h2>
            </div>
            <div className="card-body">
              <div className="row">
                <div className="form-group">
                  <label>Name</label>
                  <input
                    type="text"
                    className="form-control"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                    placeholder="Kemal"
                    required
                  />
                </div>
                <div className="form-group">
                  <label>Email</label>
                  <input
                    type="email"
                    className="form-control"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    placeholder="Kemal@gmail.com"
                    required
                  />
                </div>

                <div className="form-group">
                  <label>PhoneNo</label>
                  <input
                    type="tel"
                    className="form-control"
                    value={phoneNumber}
                    onChange={(e) => setPhoneNumber(e.target.value)}
                    placeholder="9876543210"
                    required
                  />
                </div>

                <div className="form-group">
                  <label>Password</label>
                  <input
                    type="password"
                    className="form-password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    placeholder="Password@123"
                    required
                  />
                </div>

                <div className="form-group">
                  <label htmlFor={role}>Role</label>
                  <select
                    className="form-role"
                    value={role}
                    onChange={(e) => setRole(e.target.value)}
                    required
                  >
                    <option value="ADMIN">ADMIN</option>
                    <option value="MANAGER">MANAGER</option>
                    <option value="CUSTOMER">CUSTOMER</option>
                    <option value="STAFF">STAFF</option>
                  </select>
                </div>

                <div className="form-group">
                  <button
                    type="submit"
                    className="btn btn-success"
                    onClick={handleSubmit}
                  >
                    <link to="/" />
                    Save
                  </button>
                  <Link to="/" className="btn btn-danger">
                    Back
                  </Link>
                </div>
              </div>
            </div>
          </div>
        </form>
      </div>
    </div>
  );
};

export default Login;
