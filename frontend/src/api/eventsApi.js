
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
export async function createEvent(event, imageFile) {
  const formData = new FormData();
  formData.append("event", new Blob([JSON.stringify(event)], { type: "application/json" }));
  if (imageFile) {
    formData.append("image", imageFile);
  }

  const res = await apiClient.post(API, formData, {
    headers: {
      "Content-Type": "multipart/form-data"
    }
  });
  return res.data;
}


// PUT /api/events/{id}
export async function updateEvent(id, data) {
  return apiClient.put(`${API}/${id}`, data);
}

// DELETE /api/events/{id}
export async function deleteEvent(id) {
  return apiClient.delete(`${API}/${id}`);
}
