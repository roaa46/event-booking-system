import { createContext, useContext, useState } from "react";
import { logout as logoutAPI } from "../api/authApi";
import Cookies from "universal-cookie";

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const cookies = new Cookies();

  const login = (userData) => {
    setUser(userData);
  };

  const logout = async () => {
  try {
    await logoutAPI();
  } catch (e) {
    console.error("Logout API failed:", e);
  }
  finally {
    cookies.remove("token", { path: "/" });
    setUser(null);
  }
};

  return (
    <AuthContext.Provider value={{ user, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);
