import * as React from 'react';
import Happy from '../assets/images/happy.png';
import { Card, Typography } from '@mui/material';

export default function DayDiary() {

    return (
        <div>
            <Typography style={{ fontFamily: "omyu_pretty", fontWeight: "bold", fontSize: "20px", marginLeft: "50px", marginTop: "15px" }}>2023.11.02</Typography>
            <Card style={{ height: "45px", width: "600px", marginLeft: "50px", marginTop: "10px", display: "flex", flexDirection: "row", borderRadius: "10px", boxShadow: "3px 3px 3px #eeeeee", borderStyle: "solid", borderColor: "#eeeeee", borderWidth: "1px" }}>
                <img src={Happy} alt='happy' style={{ height: "30px", marginLeft: "20px", marginTop: "7px" }} />
                <Typography style={{ fontFamily: "omyu_pretty", fontSize: "25px", marginLeft: "20px", marginTop: "5px" }}>오늘도 좋은 하루를 보내셨네요</Typography>
            </Card>
            <Card style={{ height: "250px", width: "600px", marginLeft: "50px", marginTop: "10px", borderRadius: "10px", boxShadow: "3px 3px 3px #eeeeee", borderStyle: "solid", borderColor: "#eeeeee", borderWidth: "1px" }}>
                <Typography style={{ fontFamily: "KyoboHand", marginLeft: "20px", marginTop: "20px" }}>내일이 토요일이면 좋겠다 집가서 쉬어야지</Typography>
            </Card>
        </div>
    );
}