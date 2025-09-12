
import axios from "axios";


const apiClient = axios.create({
  baseURL: import.meta.env.VITE_BASE_URL,
  withCredentials: true
});

const API = "/events";

// GET /api/events
export async function getEvents() {
  return apiClient.get(API);
}

// GET /api/events/{id}
export async function getEventById(id) {
  return apiClient.get(`${API}/${id}`);
}

// POST /api/events
export async function createEvent(formData) {
  const res = await apiClient.post("/events", formData);
  return res.data;
}

// PUT /api/events/{id}
export async function updateEvent(id, formData) {
  const res = await apiClient.put(`/events/${id}`, formData);
  return res.data;
}


// DELETE /api/events/{id}
export async function deleteEvent(id) {
  return apiClient.delete(`${API}/${id}`);
}
