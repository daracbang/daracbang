import { Button, Card, Typography } from "@mui/material";
import * as React from "react";
import Delete from "../assets/images/delete.png";
import { MusicInfoProps } from "../store/bgmReducer";
import { deleteBgm } from "../api/bgmApi";

export default function MusicInfo({ bgm, onBgmClick }: MusicInfoProps) {
  const handleDelete = async () => {
    await deleteBgm(bgm.bgmId);
  };

  const handleClick = () => {
    onBgmClick(bgm.videoId);
  };

  return (
    <Card
      style={{
        height: "50px",
        width: "380px",
        marginLeft: "20px",
        marginTop: "20px",
        display: "flex",
        flexDirection: "row",
      }}
      onClick={handleClick}
    >
      <Typography
        style={{
          fontFamily: "omyu_pretty",
          fontWeight: "bold",
          marginLeft: "20px",
          marginTop: "10px",
          width: "300px",
        }}
      >
        {bgm.bgmName}
      </Typography>
      <Button
        size="small"
        style={{ height: "20px", width: "15px", marginTop: "15px" }}
        onClick={handleDelete}
      >
        <img src={Delete} alt="Delete" />
      </Button>
    </Card>
  );
}
