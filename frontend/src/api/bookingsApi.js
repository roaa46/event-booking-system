import axios from "axios";

const apiClient = axios.create({
  baseURL: import.meta.env.VITE_BASE_URL,
  withCredentials: true
});

const API = "/users";

// POST /api/users/{personId}/bookings
export async function bookEvent(personId, eventId) {
    const res = await apiClient.post(`${API}/${personId}/bookings`, eventId, {
    headers: { "Content-Type": "application/json" }
  });
  return res.data;
}

// GET /api/users/{personId}/bookings
export async function getUserBookings(personId, page = 0, size = 10) {
  const res = await apiClient.get(`${API}/${personId}/bookings`, {
    params: { page, size }
  });
  return res.data;
}
