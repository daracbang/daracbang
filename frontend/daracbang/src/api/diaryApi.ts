import { AxiosResponse } from "axios";
import { jwtApiInstance } from "./http";

const jwtHttp = jwtApiInstance();

export const uploadDiary = async (scope: string, content: string) => {
  const URL = "/api/diaries";
  await jwtHttp.post(URL, { scope, content });
};

export interface MoodTrackerItemType {
  diaryId: string;
  createdAt: string;
  sentiment: string;
}

export const getMoodTracker = async (
  id: number,
  year: number,
  month: number
): Promise<AxiosResponse<{ moodTracker: MoodTrackerItemType[] }>> => {
  const URL = `/api/diaries/mood-tracker/${id}?year=${year}&month=${month}`;
  return await jwtHttp.get(URL);
};
