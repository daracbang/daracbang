import { Card, CardContent, Typography } from '@mui/material';
import * as React from 'react';
import Sun from '../assets/images/sun.png';
import styled from "@emotion/styled";

export default function Comment() {

    return (
        <Card style={{ height: "100px", width: "600px", marginLeft: "50px", marginTop: "15px" }}>
            <NeighInfo style={{ display: "flex", flexDirection: "row", marginTop: "10px" }}>
                <img src={Sun} alt='sun' style={{ height: "20px", marginLeft: "10px" }} />
                <Typography style={{ fontFamily: "omyu_pretty", fontWeight: "bold", marginLeft: "10px" }}>김싸피</Typography>
            </NeighInfo>
            <CardContent style={{ fontFamily: "KyoboHand" }}>안녕~!</CardContent>
        </Card>
    );
}

const NeighInfo = styled.div`

`;