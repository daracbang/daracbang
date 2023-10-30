import React from 'react';
import { BrowserRouter, Route, Routes } from "react-router-dom";
import './App.css';
import Main from "./pages/Main";
import Member from "./pages/Member";
import Daracbang from "./pages/Daracbang";
import Diary from "./pages/Diary";
import Neighbor from "./pages/Neighbor";
import Guestbook from "./pages/Guestbook";
import Login from './pages/Login';
import SignUp from './pages/SignUp';


function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Main />}></Route>
        <Route path="/members" element={<Member />}></Route>
        <Route path="/daracbang" element={<Daracbang />}></Route>
        <Route path="/diaies" element={<Diary />}></Route>
        <Route path="/neighbors" element={<Neighbor />}></Route>
        <Route path="/guestbooks" element={<Guestbook />}></Route>
        <Route path="/login" element={<Login />}></Route>
        <Route path="/signup" element={<SignUp />}></Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
