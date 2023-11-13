import axios, { AxiosInstance, Axios } from "axios";

const apiURL = process.env.REACT_APP_APPLICATION_SERVER_URL;

export const backApiInstance = () => {
  const instance: Axios = axios.create({
    baseURL: apiURL,
  });
  return instance;
};

export const jwtApiInstance = () => {
  const instance: AxiosInstance = axios.create({
    baseURL: apiURL,
  });

  instance.interceptors.request.use(
    (request) => {
      return request;
    },
    (error: any) => {
      return Promise.reject(error);
    }
  );

  return instance;
};
