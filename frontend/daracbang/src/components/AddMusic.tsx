import { Button, Card, Typography } from '@mui/material';
import * as React from 'react';
import Delete from '../assets/images/delete.png';

export default function AddMusic() {

    return (
        <Card style={{ height: "50px", width: "380px", marginLeft: "20px", marginTop: "20px", display: "flex", flexDirection: "row" }}>
            <Typography style={{ fontFamily: "omyu_pretty", fontWeight: "bold", marginLeft: "20px", marginTop: "10px", width: "300px" }}>르세라핌 - 이브와 프시케와 푸른수염의 아내</Typography>
            <Button size='small' style={{ height: "20px", width: "15px", marginTop: "15px" }}>
                <img src={Delete} alt='Delete' />
            </Button>

        </Card>
    );
}