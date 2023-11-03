import React from 'react';
import { BrowserRouter, Route, Routes } from "react-router-dom";
import './App.css';
import Main from "./pages/Main";
import Member from "./pages/Member";
import Daracbang from "./pages/Daracbang";
import Diary from "./pages/Diary";
import DiaryWrite from "./pages/DiaryWrite";
import Neighbor from "./pages/Neighbor";
import Guestbook from "./pages/Guestbook";
import SignUp from './pages/SignUp';


function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Main />}></Route>
        <Route path="/members" element={<Member />}></Route>
        <Route path="/daracbang" element={<Daracbang />}></Route>
        <Route path="/diary" element={<Diary />}></Route>
        <Route path="/diary-write" element={<DiaryWrite />}></Route>
        <Route path="/neighbors" element={<Neighbor />}></Route>
        <Route path="/guestbooks" element={<Guestbook />}></Route>
        <Route path="/signup" element={<SignUp />}></Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
