import { Card, Typography } from '@mui/material';
import * as React from 'react';
import Sun from '../assets/images/sun.png';
import styled from "@emotion/styled";

export default function NeighPost() {

    return (
        <Card style={{ height: "100px", width: "600px", marginLeft: "50px", marginTop: "15px" }}>
            <Typography style={{ height: "20px", marginLeft: "10px", fontFamily: "omyu_pretty", fontWeight: "bold" }} >2023.11.03</Typography>
            <NeighInfo style={{ display: "flex", flexDirection: "row", marginTop: "10px" }}>
                <img src={Sun} alt='sun' style={{ height: "20px", marginLeft: "10px" }} />
                <Typography style={{ fontFamily: "omyu_pretty", fontWeight: "bold", marginLeft: "10px" }}>김싸피</Typography>
            </NeighInfo>
            <Typography style={{ fontFamily: "KyoboHand", marginLeft: "10px", marginTop: "5px" }}>안녕~!</Typography>
        </Card>
    );
}

const NeighInfo = styled.div`

`;