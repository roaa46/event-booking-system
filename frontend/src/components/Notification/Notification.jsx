import "./Notification.css";

export default function Notification({ type, title, message, onClose, actions }) {
  return (
    <div className={`notification ${type}`}>
      <button className="close-btn" onClick={onClose}>×</button>
      <div className="icon">{type === "success" ? "✓" : "!"}</div>
      <h3 className="title">{title}</h3>
      <p className="message">{message}</p>
      <div className="actions">
        {actions?.map((action, index) => (
          <button key={index} className="action-btn" onClick={action.onClick}>
            {action.label}
          </button>
        ))}
      </div>
    </div>
  );
}
