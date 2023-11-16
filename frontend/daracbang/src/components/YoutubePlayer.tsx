// YoutubePlayer 컴포넌트
import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import YouTube, { YouTubeProps } from "react-youtube";
import { RootState } from "../store/rootReducer";

interface YoutubePlayerProps {
  videoId: string;
  onReady: (event: { target: any }) => void;
}

export default function YoutubePlayer({
  videoId,
  onReady,
}: YoutubePlayerProps) {
  const bgmLists = useSelector((state:RootState) => {
    return state.BGMReducer.bgmList;
  })

  const onPlayerReady: YouTubeProps["onReady"] = (event) => {
    event.target.pauseVideo();
  };

  const opts: YouTubeProps["opts"] = {
    height: "100px",
    width: "100px",
    playerVars: {
      autoplay: 1,
    },
  };

  return (
    <div>
      {bgmLists.length >= 1 ? <YouTube videoId={bgmLists[0].videoId} opts={opts} onReady={()=> {}} /> : <></> }
      ;
    </div>
  );
}
