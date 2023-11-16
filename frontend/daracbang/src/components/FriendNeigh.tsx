import { Button, Card, Typography } from "@mui/material";
import * as React from "react";
import Ban from "../assets/images/ban.png";
import styled from "@emotion/styled";
import { NeighborObject } from "../api/neighborApi";

interface FriendNeighProps {
  data: NeighborObject;
  onRemove: (id: number) => void;
}

const FriendNeigh: React.FC<FriendNeighProps> = ({ data, onRemove }) => {
  return (
    <div>
      <Card
        style={{
          height: "50px",
          width: "360px",
          margin: "15px auto",
          boxShadow: "2px 2px 1px 1px #eeeeee",
          borderRadius: "10px",
        }}
      >
        <NeighInfo style={{ display: "flex", flexDirection: "row", marginTop: "10px", marginLeft: "10px" }}>
          <img src={data?.profileImage} alt="sun" style={{ height: "20px", marginLeft: "10px" }} />
          <Typography
            style={{
              fontFamily: "omyu_pretty",
              fontWeight: "bold",
              width: "100px",
              marginLeft: "10px",
              marginRight: "100px",
              lineHeight: "30px",
            }}
          >
            {data?.nickname}
          </Typography>
          <Button onClick={() => onRemove(data.neighborId)} style={{ padding: 0 }}>
            <img src={Ban} alt="ban" style={{ height: "20px" }} />
          </Button>
        </NeighInfo>
      </Card>
    </div>
  );
};

const NeighInfo = styled.div``;

export default FriendNeigh;
