import { Link } from "react-router-dom";
import React from 'react';
import Logo from "../assets/images/logo.png";
import styled from "@emotion/styled";
import { Button, Typography } from "@mui/material";
import Music from "../assets/images/music.png";
import Bell from "../assets/images/bell.png";

const Head = () => {

    return (
        <Header>
            <Logos>
                <Link to="/Daracbang" style={{ textDecoration: "none", marginRight: "20px" }}>
                    <img src={Logo} alt="logo" />
                </Link>
                <Typography style={{ fontFamily: "omyu_pretty", fontSize: "30px", fontWeight: "bold", marginTop: "5px" }}>다락방</Typography>
            </Logos>
            <BtnGroup >
                <Button><img src={Music} alt="music" style={{ height: "70px", width: "60px" }} /></Button>
                <Button><img src={Bell} alt="bell" /></Button>
                <Link to="/Login" style={{ textDecoration: "none", marginTop: "7px", fontFamily: "omyu_pretty", fontSize: "20px", color: "inherit" }}>로그인</Link>
            </BtnGroup>
        </Header>
    );
};

const Header = styled.header`
  display: flex;
  justify-content: space-between;
  height: 50px;
  margin: 15px;
`;

const BtnGroup = styled.div`
    margin-right: 20px;
    display: flex;
    flex-direction: row;
`;
const Logos = styled.div`        
    display: flex;
    flex-direction: row;
`;


export default Head;
