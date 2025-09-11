import axios from "axios";


const apiClient = axios.create({
  baseURL: import.meta.env.VITE_BASE_URL,
  withCredentials: true // to send cookie auto with each request
});

const API = "/pending/admins";

// GET /api/pending/admins
export async function getPendingAdmins() {
  const res = await apiClient.get(`${API}`);
  return res.data; // List<PersonResponseDTO>
}

// PUT /api/pending/admins/{id}?action=approve|reject
export async function handleAdminAction(id, action) {
  const res = await apiClient.put(`${API}/${id}`, null, {
    params: { action }
  });
  return res.data; // approve → PersonResponseDTO, reject → empty (204)
}
