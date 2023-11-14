import React, { useEffect, useState } from "react";
import styled from "@emotion/styled";
import MyDarac from "../assets/images/room2.png";
import Head from "../components/Head";
import { Button, Card, CardContent, Typography } from "@mui/material";
import LinearProgress, { linearProgressClasses } from "@mui/material/LinearProgress";
import { Link, useParams } from "react-router-dom";
import Dial from "../components/SpeedDial";
import Happy from "../assets/images/happy.png";
import Think from "../assets/images/thinking.png";
import Angry from "../assets/images/angry.png";
import MoodTracker from "../components/MoodTracker";
import { useSelector } from "react-redux";
import { RootState } from "../store/rootReducer";
import { otherMemberInfo } from "../api/memberApi";
import { MemberInfo } from "../store/memberReducer";

const BorderLinearProgress = styled(LinearProgress)(({ theme }) => ({
  height: 15,
  width: 300,
  marginLeft: 10,
  marginBottom: 10,
  borderRadius: 8,
  [`&.${linearProgressClasses.colorPrimary}`]: {
    backgroundColor: "theme.palette.grey[theme.palette.mode === 'light' ? 200 : 800]",
  },
  [`& .${linearProgressClasses.bar}`]: {
    borderRadius: 8,
    backgroundColor: "theme.palette.mode === 'light' ? '#1a90ff' : '#308fe8'",
  },
}));

const Daracbang: React.FC = () => {
  const [daracMemberInfo, setDaracMemberInfo] = useState<MemberInfo>();
  const member = useSelector((state: RootState) => {
    return state.memberReducer.member;
  });
  const params = useParams();

  useEffect(() => {
    async function getOtherMemberInfo(id: number) {
      const res = await otherMemberInfo(id);
      setDaracMemberInfo(res.data);
    }
    if (params.memberId) {
      getOtherMemberInfo(parseInt(params.memberId));
      if (daracMemberInfo && daracMemberInfo.id === member?.id) {
        console.log("본인 글입니다.!!"); ///  단순 본인인지 체크 용, 삭제해도 무방.
      }
    }
  }, []);

  return (
    <div>
      <Head />
      <ContainerWrap style={{ backgroundColor: "#F2EBEB" }}>
        <SideWrap>
          <Intro>
            <Card
              style={{
                height: "80px",
                marginBottom: "15px",
                borderRadius: "10px",
                boxShadow: "3px 3px 5px 1px #bdbdbd",
              }}
            >
              <Typography style={{ fontFamily: "omyu_pretty", fontWeight: "bold", fontSize: "20px" }}>
                {daracMemberInfo?.nickname} 님 다락방 소개글
              </Typography>
            </Card>
          </Intro>
          <Emotions>
            <Card style={{ height: "130px", borderRadius: "10px", boxShadow: "3px 3px 5px 1px #bdbdbd" }}>
              <Typography
                marginBottom={1}
                style={{ fontFamily: "omyu_pretty", fontWeight: "bold", fontSize: "20px", marginBottom: 0 }}
              >
                나의 감정상태
              </Typography>
              <CardContent style={{ marginLeft: "10px" }}>
                <MoodIcon>
                  <img src={Happy} alt="happy" style={{ height: "20px", width: "20px", marginRight: "5px" }} />
                  <BorderLinearProgress variant="determinate" value={50} style={{ marginTop: "2px" }} />
                </MoodIcon>
                <MoodIcon>
                  <img src={Think} alt="think" style={{ height: "20px", width: "20px", marginRight: "5px" }} />
                  <BorderLinearProgress variant="determinate" value={50} style={{ marginTop: "2px" }} />
                </MoodIcon>
                <MoodIcon>
                  <img src={Angry} alt="angry" style={{ height: "20px", width: "20px", marginRight: "5px" }} />
                  <BorderLinearProgress variant="determinate" value={50} style={{ marginTop: "2px" }} />
                </MoodIcon>
              </CardContent>
            </Card>
          </Emotions>
          <MoodTracker />
          <SumDiary>
            <Card style={{ height: "100px", borderRadius: "10px", boxShadow: "3px 3px 5px 1px #bdbdbd" }}>
              <CardContent style={{ fontFamily: "omyu_pretty", fontWeight: "bold", fontSize: "15px" }}>
                오늘의 일기
              </CardContent>
              <Link to={"/diary"}>
                <Button
                  size="small"
                  variant="contained"
                  style={{ fontFamily: "omyu_pretty", fontWeight: "bold", fontSize: "15px" }}
                >
                  더 읽으러 가기
                </Button>
              </Link>
            </Card>
          </SumDiary>
        </SideWrap>

        <img src={MyDarac} alt="myDarac" />

        <Navi style={{ transform: "translateZ(0px)", flexGrow: 1 }}>
          <Dial />
        </Navi>
      </ContainerWrap>
    </div>
  );
};

const ContainerWrap = styled.div`
  display: flex;
  flex-direction: row;
  padding-top: 17px;
  padding-bottom: 25px;
  padding-left: 50px;
  padding-right: 50px;
`;

const SideWrap = styled.div`
  margin-right: 10%;
  text-align: center;
`;

const Intro = styled.div`
  width: 400px;
`;

const Emotions = styled.div`
  width: 400px;
`;

const SumDiary = styled.div`
  width: 400px;
`;

const Navi = styled.div``;

const MoodIcon = styled.div`
  display: flex;
  flex-direction: "row";
`;

export default Daracbang;
