import React,  from "react";
import { Card, Typography } from "@mui/material";
import { DiaryDetail } from "../api/diaryApi";
import { formatDate } from "../utils/dateUtil";
import HappyURL from "../assets/images/happy.png";
import ThinkingURL from "../assets/images/thinking.png";
import AngryURL from "../assets/images/angry.png";

export type DayDiaryProps = {
  diary: DiaryDetail;
};

const getMoodEmozi = (sentiment: string) => {
  let url = HappyURL;
  if (sentiment === "neutral") {
    url = ThinkingURL;
  }
  if (sentiment === "negative") {
    url = AngryURL;
  }
  return url;
};

const DayDiary: React.FC<DayDiaryProps> = ({ diary }: DayDiaryProps) => {
  return (
    <div>
      <Typography
        style={{
          fontFamily: "omyu_pretty",
          fontWeight: "bold",
          fontSize: "20px",
          marginLeft: "50px",
          marginTop: "15px",
          marginRight: "50px",
        }}
      >
        <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center" }}>
          {formatDate(diary?.createdAt)}
          <img
            src={getMoodEmozi(diary.sentimentResult)}
            alt="happy"
            style={{ height: "30px", marginLeft: "20px", marginTop: "7px" }}
          />
        </div>
      </Typography>

      <Card
        style={{
          maxHeight: "250px",
          width: "600px",
          marginLeft: "50px",
          marginTop: "10px",
          borderRadius: "10px",
          boxShadow: "3px 3px 3px #eeeeee",
          borderStyle: "solid",
          borderColor: "#eeeeee",
          borderWidth: "1px",
          overflowY: "scroll",
        }}
      >
        <Typography style={{ fontFamily: "KyoboHand", marginLeft: "20px", marginTop: "20px" }}>
          {diary?.content}
        </Typography>
      </Card>
    </div>
  );
};

export default DayDiary;
