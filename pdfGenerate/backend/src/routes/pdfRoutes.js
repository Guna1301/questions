import { Router } from "express";
import PDFDocument from "pdfkit";
import { User } from "../models/User.js";


const router = Router();

const docs = [
  {
    rollNumber: "22BD1A0501",
    name: "Guna Sai",
    email: "guna.sai@example.com",
    age: 20,
    branch: "CSE",
    section: "A",
    cgpa: 8.5
  },
  {
    rollNumber: "22BD1A0502",
    name: "Bhanu Teja",
    email: "bhanu.teja@example.com",
    age: 21,
    branch: "ECE",
    section: "B",
    cgpa: 7.9
  },
  {
    rollNumber: "22BD1A0503",
    name: "Ansh Reddy",
    email: "ansh.reddy@example.com",
    age: 22,
    branch: "EEE",
    section: "C",
    cgpa: 8.2
  },
  {
    rollNumber: "22BD1A0504",
    name: "Siri Priya",
    email: "siri.priya@example.com",
    age: 20,
    branch: "IT",
    section: "A",
    cgpa: 9.1
  },
  {
    rollNumber: "22BD1A0505",
    name: "Karthik Rao",
    email: "karthik.rao@example.com",
    age: 23,
    branch: "MECH",
    section: "B",
    cgpa: 7.5
  },
  {
    rollNumber: "22BD1A0506",
    name: "Meghana Devi",
    email: "meghana.devi@example.com",
    age: 21,
    branch: "CIVIL",
    section: "C",
    cgpa: 8.7
  },
  {
    rollNumber: "22BD1A0507",
    name: "Rohit Varma",
    email: "rohit.varma@example.com",
    age: 24,
    branch: "CSE",
    section: "A",
    cgpa: 8.0
  },
  {
    rollNumber: "22BD1A0508",
    name: "Chaitanya",
    email: "chaitanya@example.com",
    age: 22,
    branch: "IT",
    section: "B",
    cgpa: 9.3
  },
  {
    rollNumber: "22BD1A0509",
    name: "Harika",
    email: "harika@example.com",
    age: 20,
    branch: "ECE",
    section: "C",
    cgpa: 8.9
  },
  {
    rollNumber: "22BD1A0510",
    name: "Vamsi Krishna",
    email: "vamsi.krishna@example.com",
    age: 23,
    branch: "MECH",
    section: "B",
    cgpa: 7.8
  }
];

router.get("/health", (_req, res) => res.json({ ok: true }));


router.post("/seed", async (_req, res) => {
    try {
        await User.deleteMany({});
        await User.insertMany(docs);
        res.json({ inserted: docs.length, ids: docs.map(d => d.rollNumber) });
    } catch (e) {
        console.error(e);
        res.status(500).json({ error: "Seeding failed" });
    }
});

router.get("/user/:rollNumber", async (req, res) => {
    const { rollNumber } = req.params;
    const user = await User.findOne({ rollNumber });
    if (!user) return res.status(404).json({ error: "No data found" });
    res.json(user);
});


router.get("/pdf/:rollNumber", async (req, res) => {
  try {
    const { rollNumber } = req.params;
    const user = await User.findOne({ rollNumber: rollNumber.toUpperCase() });

    if (!user) return res.status(404).send("No data found");

    const doc = new PDFDocument({ size: "A4", margin: 50 });

    res.setHeader("Content-Type", "application/pdf");
    res.setHeader("Content-Disposition", `inline; filename=${rollNumber}.pdf`);

    doc.pipe(res);

    doc.info.Title = `Data of ${rollNumber}`;
    doc.info.Author = "guna";

    doc.fontSize(18).text(`User Report: ${user.rollNumber}`, { align: "center" });
    doc.moveDown();

    doc.fontSize(14);
    Object.entries(user.toObject()).forEach(([key, value]) => {
      if (["_id", "__v", "createdAt", "updatedAt"].includes(key)) return;
      doc.text(`${key.charAt(0).toUpperCase() + key.slice(1)}: ${value}`);
    });

    doc.moveDown(2);
    doc.fontSize(10).text(`Generated: ${new Date().toLocaleString()}`, { align: "right" });

    doc.end();
  } catch (err) {
    console.error(err);
    res.status(500).send("Error generating PDF");
  }
});

export default router;
