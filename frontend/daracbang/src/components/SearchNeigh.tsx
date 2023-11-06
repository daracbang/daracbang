import { Button, Card, Typography } from '@mui/material';
import * as React from 'react';
import Ban from '../assets/images/ban.png';
import Plus from '../assets/images/plus.png';
import Sun from '../assets/images/sun.png';
import styled from "@emotion/styled";

export default function SearchNeigh() {

    return (
        <Card style={{ height: "70px", width: "360px", margin: "15px auto", boxShadow: "2px 2px 1px 1px #eeeeee", borderRadius: "10px" }}>
            <NeighInfo style={{ display: "flex", flexDirection: "row", marginTop: "10px", marginLeft: "10px" }}>
                <img src={Sun} alt='sun' style={{ height: "20px", marginLeft: "10px" }} />
                <Typography style={{ fontFamily: "omyu_pretty", fontWeight: "bold", width: "100px", marginLeft: "10px", marginRight: "100px" }}>김싸피</Typography>
                <Button style={{ marginLeft: "30px", padding: 0 }}><img src={Plus} alt='plus' style={{ height: "20px" }} /></Button>
                <Button style={{ padding: 0 }}><img src={Ban} alt='ban' style={{ height: "20px" }} /></Button>
            </NeighInfo>
            <Typography style={{ fontFamily: 'omyu_pretty', marginLeft: "20px" }}>놀러오세요 마이 다락방!</Typography>
        </Card>
    );
}

const NeighInfo = styled.div`

`;