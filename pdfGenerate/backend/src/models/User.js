import mongoose from "mongoose";

const UserSchema = new mongoose.Schema(
  {
    rollNumber: { type: String, required: true, unique: true, index: true },
    name: { type: String, required: true },
    email: { type: String, required: true },
    age: { type: Number, required: true },
    branch: { type: String, required: true },
    section: { type: String, required: true },
    cgpa: { type: Number, required: true, min: 0, max: 10 },
  },
  { timestamps: true }
);


export const User = mongoose.model("User", UserSchema);