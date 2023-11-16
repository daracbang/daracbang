import { Link } from "react-router-dom";
import React from "react";
import Logo from "../assets/images/logo.png";
import styled from "@emotion/styled";
import { Avatar, Badge, Button, Card, Popover, Typography } from "@mui/material";
import Music from "../assets/images/music.png";
import Bell from "../assets/images/bell.png";
import CheckAlert from "../assets/images/checkAlert.png";
import Mute from "../assets/images/mute.png";
import { useDispatch } from "react-redux";
import { logoutAction } from "../store/memberReducer";
import * as tokenUtil from "../utils/tokenUtil";

const SmallAvatar = styled(Avatar)(({ theme }) => ({
  width: 22,
  height: 22,
  bottom: 32,
  right: 13,
}));

const Head = () => {
  const [anchorEl, setAnchorEl] = React.useState<HTMLButtonElement | null>(null);
  const dispatch = useDispatch();

  const handleClick = (event: React.MouseEvent<HTMLButtonElement>) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  const logout = () => {
    dispatch(logoutAction());
    tokenUtil.deleteToken();
  };
  const open = Boolean(anchorEl);

  const [mute, setMute] = React.useState(false);
  const [mPic, setMpic] = React.useState("Music");

  const muteClickHandler = () => {
    if (mute === true) {
      setMute(false);
      setMpic("Music");
    } else {
      setMute(true);
      setMpic("Mute");
    }
  };

  return (
    <Header style={{ backgroundColor: "#F2EBEB" }}>
      <Logos>
        <Link to="/daracbang" style={{ textDecoration: "none", marginRight: "20px" }}>
          <img src={Logo} alt="logo" />
        </Link>
        <Typography style={{ fontFamily: "omyu_pretty", fontSize: "30px", fontWeight: "bold", marginTop: "5px" }}>
          다락방
        </Typography>
      </Logos>
      <BtnGroup>
        {/* <Card style={{ borderRadius: "30px", width: "280px", height: "30px", marginTop: "10px" }}>
          <Typography style={{ fontFamily: "omyu_pretty", textAlign: "center", marginTop: "2px" }}>
            르세라핌 - 이브와 프시케와 푸른수염의 아내
          </Typography>
        </Card>
        <Button onClick={muteClickHandler}>
          {mPic === "Music" ? (
            <img src={Music} alt="music" style={{ height: "60px", width: "70px" }} />
          ) : (
            <img src={Mute} alt="mute" style={{ height: "60px", width: "70px" }} />
          )}
        </Button> */}

        {/* <Badge
          overlap="circular"
          anchorOrigin={{ vertical: "bottom", horizontal: "right" }}
          badgeContent={<SmallAvatar alt="Remy Sharp" src={CheckAlert} />}
        >
          <Button onClick={handleClick}>
            <img src={Bell} alt="bell" />
          </Button>
          <Popover
            open={open}
            onClick={handleClose}
            anchorPosition={{ top: 190, left: 800 }}
            anchorOrigin={{
              vertical: "bottom",
              horizontal: "center",
            }}
            transformOrigin={{
              vertical: "top",
              horizontal: "right",
            }}
          >
            The content of the Popover.
          </Popover>
        </Badge> */}

        <Link
          onClick={logout}
          to="/"
          style={{
            textDecoration: "none",
            marginTop: "7px",
            fontFamily: "omyu_pretty",
            fontSize: "20px",
            color: "inherit",
          }}
        >
          로그아웃
        </Link>
      </BtnGroup>
    </Header>
  );
};

const Header = styled.header`
  backgroung-color: F2EBEB;
  display: flex;
  justify-content: space-between;
  height: 50px;
  padding: 15px;
`;

const BtnGroup = styled.div`
  margin-right: 40px;
  display: flex;
  flex-direction: row;
`;
const Logos = styled.div`
  display: flex;
  flex-direction: row;
`;

export default Head;
