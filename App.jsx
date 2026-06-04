import logo from "./logo.svg";
import "./App.css";
import NavBar from "./NavBar/NavBar";
import Home from "./NavBar/Home/Home";
import Login from "./NavBar/Home/Login";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { ToastContainer } from "react-toastify";
function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <ToastContainer />
        <Routes>
          <Route path="/" element={<NavBar />} />
          <Route path="/user/saveUser" element={<Login />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
