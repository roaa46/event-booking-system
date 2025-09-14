import axios from "axios";

console.log("BASE_URL:", import.meta.env.VITE_BASE_URL);
// axios instance
const apiClient = axios.create({
  baseURL: import.meta.env.VITE_BASE_URL,
  withCredentials: true // to send cookie auto with each request
});


const API = "/auth";

// POST /api/auth/register
export async function register(data) {
  const res = await apiClient.post(`${API}/register`, data);
  return res.data;
}

// POST /api/auth/login
export async function login(data) {
  const res = await apiClient.post(`${API}/login`, data);
  return res.data;
}

// POST /api/auth/logout
export async function logout() {
  const res = await apiClient.post(`${API}/logout`);
  return res.data;
}

// GET /api/auth/me
export async function viewProfile() {
  const res = await apiClient.get(`${API}/me`);
  return res.data;
}

// DELETE /api/auth/{id}
export async function deleteProfile() {
  const res = await apiClient.delete("${API}/me");
  return res.data;
}
