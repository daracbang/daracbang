import { SpeedDial, SpeedDialAction, SpeedDialIcon } from '@mui/material';
import DiaryBook from "../assets/images/book.png";
import PostIt from "../assets/images/postit.png";
import Room from "../assets/images/room3.png";
import React from 'react';


const actions: { icon: React.ReactNode; name: string, link: string; }[] = [
    { icon: <img src={Room} alt="Room" style={{ height: "90px", width: "90px" }} />, name: '이웃 다락방', link: "/neighbor", },
    { icon: <img src={PostIt} alt="Post It" style={{ height: "80px", width: "80px" }} />, name: '방명록', link: "/guestbook" },
    { icon: <img src={DiaryBook} alt="Diary Book" style={{ height: "80px", width: "80px" }} />, name: '오늘 다이어리 작성', link: "/diary-write" },

];

const actionSize = {
    width: 50,
    height: 50,
    margin: "30px",
    backgroundColor: 'inherit',

}


const Dial: React.FC = () => {

    const handleSpeedDialClick = (link: string) => {
        // 클릭할 때 이동하려는 페이지로 리디렉션합니다.
        window.location.href = link;
    };

    return (
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
                    tooltipPlacement='bottom'
                    sx={actionSize}
                    onClick={() => handleSpeedDialClick(action.link)}
                />
            ))}
        </SpeedDial>
    );
};

export default Dial;