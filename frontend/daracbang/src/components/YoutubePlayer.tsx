// YoutubePlayer 컴포넌트
import { useEffect, useState } from "react";
import YouTube, { YouTubeProps } from "react-youtube";

interface YoutubePlayerProps {
  videoId: string;
  onReady: (event: { target: any }) => void;
}

export default function YoutubePlayer({
  videoId,
  onReady,
}: YoutubePlayerProps) {
  const onPlayerReady: YouTubeProps["onReady"] = (event) => {
    event.target.pauseVideo();
  };

  const opts: YouTubeProps["opts"] = {
    height: "100",
    width: "100",
    playerVars: {
      autoplay: 1,
    },
  };

  return (
    <div>
      <YouTube videoId={videoId} opts={opts} onReady={onReady} />;
    </div>
  );
}
