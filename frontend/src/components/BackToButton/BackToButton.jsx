import { useNavigate } from "react-router-dom";
import "./BackToButton.css";
import useAuth from "../../hooks/useAuth"

export default function BackToButton() {
    const navigate = useNavigate();
    const { user, loading } = useAuth();
    if (loading) return null;
    return (
        <>
            {user.role === "USER" && (
                <button
                    className="btn btn-back"
                    onClick={() => navigate("/events")}
                >
                    ← Back to Events
                </button>
            )}

            {user.role === "ADMIN" && (
                <button
                    className="btn btn-back"
                    onClick={() => navigate("/admins/dashboard")}
                >
                    ← Back to Dashboard
                </button>
            )}

        </>
    );
}
