import React from 'react';
import styled from "@emotion/styled";
import MyDarac from "../assets/images/room2.png";
import Head from '../components/Head';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { DateCalendar } from '@mui/x-date-pickers/DateCalendar';
import { Button, Card, CardContent, SpeedDial, SpeedDialAction, SpeedDialIcon, Typography } from '@mui/material';
import DiaryBook from "../assets/images/book.png";
import PostIt from "../assets/images/postit.png";
import Room from "../assets/images/room3.png";
import LinearProgress, { linearProgressClasses } from '@mui/material/LinearProgress';


const actions: { icon: React.ReactNode; name: string }[] = [
    { icon: <img src={Room} alt="Room" style={{ height: "90px", width: "90px" }} />, name: '이웃 다락방' },
    { icon: <img src={PostIt} alt="Post It" style={{ height: "80px", width: "80px" }} />, name: '방명록' },
    { icon: <img src={DiaryBook} alt="Diary Book" style={{ height: "80px", width: "80px" }} />, name: '오늘 다이어리 작성' },

];

const actionSize = {
    width: 50,
    height: 50,
    margin: "30px",
    backgroundColor: 'inherit'
}

const BorderLinearProgress = styled(LinearProgress)(({ theme }) => ({
    height: 10,
    borderRadius: 5,
    [`&.${linearProgressClasses.colorPrimary}`]: {
        backgroundColor: "theme.palette.grey[theme.palette.mode === 'light' ? 200 : 800]",
    },
    [`& .${linearProgressClasses.bar}`]: {
        borderRadius: 5,
        backgroundColor: "theme.palette.mode === 'light' ? '#1a90ff' : '#308fe8'",
    },
}));

const Daracbang: React.FC = () => {

    return (
        <div>
            <Head />
            <ContainerWrap style={{ backgroundColor: "#F2EBEB" }}>
                <SideWrap>
                    <Intro>
                        <Card>
                            <Typography>다락방 소개글</Typography>
                        </Card>
                    </Intro>
                    <Emotions>
                        <Card>
                            <CardContent>
                                <BorderLinearProgress variant="determinate" value={50} />
                                <BorderLinearProgress variant="determinate" value={50} />
                                <BorderLinearProgress variant="determinate" value={50} />
                            </CardContent>
                        </Card>
                    </Emotions>
                    <Calendar>
                        <LocalizationProvider dateAdapter={AdapterDayjs}>
                            <DateCalendar />
                        </LocalizationProvider>
                    </Calendar>
                    <SumDiary>
                        <Card>
                            <CardContent>오늘의 일기</CardContent>
                            <Button>더 읽으러 가기</Button>
                        </Card>
                    </SumDiary>
                </SideWrap>


                <img src={MyDarac} alt='myDarac' />
                <Navi style={{ transform: 'translateZ(0px)', flexGrow: 1 }}>
                    <SpeedDial
                        ariaLabel="SpeedDial basic example"
                        style={{ position: 'absolute', bottom: 16, right: 16 }}
                        icon={<SpeedDialIcon />}
                    >
                        {actions.map((action) => (
                            <SpeedDialAction
                                FabProps={{ size: 'large' }}
                                key={action.name}
                                icon={action.icon}
                                tooltipTitle={action.name}
                                sx={actionSize}
                            />
                        ))}
                    </SpeedDial>
                </Navi>

            </ContainerWrap>
        </div>
    );
};

const ContainerWrap = styled.div`
 display: flex;
 flex-direction: row;
 padding-top: 40px;
 padding-bottom: 40px;
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

const Calendar = styled.div`
width: 400px;
`;

const SumDiary = styled.div`
width: 400px;
`;

const Navi = styled.div`

`;

export default Daracbang;
