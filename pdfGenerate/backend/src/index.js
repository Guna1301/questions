import "dotenv/config";
import express from "express";
import cors from "cors";
import { connectDB } from "./db.js";
import pdfRoutes from "./routes/pdfRoutes.js";


const app = express();
app.use(cors());
app.use(express.json());


app.use("/api", pdfRoutes);


const PORT = process.env.PORT || 5000;


connectDB(process.env.MONGODB_URI)
.then(() => {
app.listen(PORT, () => console.log(`Server http://localhost:${PORT}`));
})
.catch((err) => {
console.error("Mongo connection failed", err);
process.exit(1);
});