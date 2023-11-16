import { Button, Card, Typography } from "@mui/material";
import * as React from "react";
import Ban from "../assets/images/ban.png";
import Plus from "../assets/images/plus.png";
import styled from "@emotion/styled";
import { MemberSearchObject } from "../api/neighborApi";

interface SearchNeighProps {
  data: MemberSearchObject;
  onApplicate: (id: number) => void;
  onCancel: (id: number) => void;
}

const SearchNeigh: React.FC<SearchNeighProps> = ({ data, onApplicate, onCancel }) => {
  return (
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
        <img src={data?.profileImage} alt="sun" style={{ height: "30px", marginLeft: "10px", width: "50px" }} />
        <Typography
          style={{
            fontFamily: "omyu_pretty",
            fontWeight: "bold",
            width: "100px",
            marginLeft: "10px",
            lineHeight: "30px",
            whiteSpace: "nowrap",
          }}
        >
          {data.nickname}
        </Typography>
        {data.isNeighborRequest ? (
          <div style={{ display: "flex", justifyContent: "center", alignContent: "center" }}>
            <Typography
              style={{
                fontFamily: "omyu_pretty",
                fontWeight: "bold",
                width: "100px",
                marginLeft: "50px",
                textAlign: "center",
                lineHeight: "30px",
              }}
            >
              신청 대기중
            </Typography>
            <Button onClick={() => onCancel(data.memberId)} style={{}}>
              <img src={Ban} alt="plus" style={{ height: "20px" }} />
            </Button>
          </div>
        ) : data.isNeighbor ? (
          <Typography
            style={{
              fontFamily: "omyu_pretty",
              fontWeight: "bold",
              width: "100px",
              marginLeft: "50px",
              lineHeight: "30px",
            }}
          >
            이미 이웃입니다!
          </Typography>
        ) : (
          <>
            <Button onClick={() => onApplicate(data.memberId)} style={{ marginLeft: "120px" }}>
              <img src={Plus} alt="plus" style={{ height: "20px" }} />
            </Button>
          </>
        )}
      </NeighInfo>
    </Card>
  );
};

const NeighInfo = styled.div``;

export default SearchNeigh;
