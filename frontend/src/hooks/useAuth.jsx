import { useEffect, useState } from "react";
import { viewProfile } from "../api/authApi";
import { useNavigate } from "react-router-dom";

export default function useAuth(requiredRole) {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    const checkAuth = async () => {
      try {
        const res = await viewProfile();
        if (!res || !res.role) {
          navigate("/login");
          return;
        }
        if (requiredRole && res.role !== requiredRole) {
          navigate("/unauthorized");
          return;
        }
        setUser(res);
      } catch (err) {
        navigate("/login");
      } finally {
        setLoading(false);
      }
    };
    checkAuth();
  }, [navigate, requiredRole]);

  return { user, loading };
}
