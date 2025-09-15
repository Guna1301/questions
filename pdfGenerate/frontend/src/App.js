import { useState } from "react";


export default function App() {
const [query, setQuery] = useState("");


const handleGenerate = () => {
if (!query.trim()) return;
  window.open(`http://localhost:5000/api/pdf/${encodeURIComponent(query.trim())}`, "_blank");
};


return (
  <div style={{ display: "grid", placeItems: "center", minHeight: "100vh", padding: 24, color:"white" }}>
    <div style={{ width: 480, maxWidth: "90%", border: "1px solid #e5e7eb", borderRadius: 16, padding: 24, boxShadow: "0 6px 24px rgba(0,0,0,0.08)" }}>
      <h2 style={{ marginTop: 0 }}>Generate User PDF</h2>
      <p style={{ marginTop: 0 }}>Enter rollNumber (try <code>22BD1A05**</code>)</p>
      <div style={{ display: "flex", gap: 12 }}>
        <input
        placeholder="Enter rollNumber"
        value={query}
        onChange={(e) => setQuery(e.target.value)}
        onKeyDown={(e) => e.key === "Enter" && handleGenerate()}
        style={{ flex: 1, padding: 12, borderRadius: 10, border: "1px solid #d1d5db" }}
        required
        />
        <button onClick={handleGenerate} style={{ padding: "12px 16px", borderRadius: 10, border: 0, cursor: "pointer" }}>
          Generate
        </button>
      </div>
    </div>
  </div>
);
}