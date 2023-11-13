const TOKEN_KEY = "darac_token";

export const getToken = () => {
  return window.sessionStorage.getItem(TOKEN_KEY);
};
