import { useState } from "react";

const ValidForm = () => {
  const [formData, setFormData] = useState({
    username: "",
    email: "",
    password: ""
  });

  const [errors, setErrors] = useState({
    username: [],
    email: [],
    password: []
  });

  const [submittedUsers, setSubmittedUsers] = useState([]);

  const validateUsername = (username) => {
    const usernameErrors = [];
    const usernameRegex = /^[a-zA-Z0-9@#]+$/;

    if (!username) {
      usernameErrors.push("Username is required.");
    } else {
      if (!usernameRegex.test(username)) {
        usernameErrors.push("Only letters, numbers, @, and # are allowed.");
      }
    }

    return usernameErrors;
  };

  const validateEmail = (email) => {
    const emailErrors = [];
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (!email) {
      emailErrors.push("Email is required.");
    } else if (!emailRegex.test(email)) {
      emailErrors.push("Enter a valid email address.");
    }

    return emailErrors;
  };

  const validatePassword = (password) => {
    const passwordErrors = [];
    if (!password) {
      passwordErrors.push("Password is required.");
    } else {
      if (password.length < 8) {
        passwordErrors.push("Password must be at least 8 characters long.");
      }
      if (!/[A-Z]/.test(password)) {
        passwordErrors.push("Must contain at least one uppercase letter.");
      }
      if (!/[0-9]/.test(password)) {
        passwordErrors.push("Must contain at least one number.");
      }
      if (!/[!@#$%^&*]/.test(password)) {
        passwordErrors.push("Must contain at least one special symbol.");
      }
    }

    return passwordErrors;
  };

  const handleChange = (e) => {
    const { name, value } = e.target;

    setFormData((prev) => ({
      ...prev,
      [name]: value
    }));

    let fieldErrors = [];

    if (name === "username") fieldErrors = validateUsername(value);
    else if (name === "email") fieldErrors = validateEmail(value);
    else if (name === "password") fieldErrors = validatePassword(value);

    setErrors((prev) => ({
      ...prev,
      [name]: fieldErrors
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const usernameErrors = validateUsername(formData.username);
    const emailErrors = validateEmail(formData.email);
    const passwordErrors = validatePassword(formData.password);

    setErrors({
      username: usernameErrors,
      email: emailErrors,
      password: passwordErrors
    });

    const hasErrors =
      usernameErrors.length > 0 ||
      emailErrors.length > 0 ||
      passwordErrors.length > 0;

    if (!hasErrors) {
      setSubmittedUsers((prev) => [
        ...prev,
        {
          username: formData.username,
          email: formData.email
        }
      ]);

      setFormData({
        username: "",
        email: "",
        password: ""
      });
    }
  };

  return (
    <div className="p-4 max-w-md mx-auto">
      <h1 className="text-3xl font-bold mb-4">Input Form</h1>
      <form onSubmit={handleSubmit} className="space-y-6">
        <div>
          <label className="block text-lg font-semibold">Username</label>
          <input
            name="username"
            type="text"
            value={formData.username}
            onChange={handleChange}
            className="border p-2 w-full"
          />
          {errors.username.map((err, i) => (
            <p className="text-red-600 text-sm" key={i}>{err}</p>
          ))}
        </div>

        <div>
          <label className="block text-lg font-semibold">Email</label>
          <input
            name="email"
            type="email"
            value={formData.email}
            onChange={handleChange}
            className="border p-2 w-full"
          />
          {errors.email.map((err, i) => (
            <p className="text-red-600 text-sm" key={i}>{err}</p>
          ))}
        </div>

        <div>
          <label className="block text-lg font-semibold">Password</label>
          <input
            name="password"
            type="password"
            value={formData.password}
            onChange={handleChange}
            className="border p-2 w-full"
          />
          {errors.password.map((err, i) => (
            <p className="text-red-600 text-sm" key={i}>{err}</p>
          ))}
        </div>

        <button
          type="submit"
          className="bg-blue-600 text-white px-4 py-2 rounded"
        >
          Submit
        </button>
      </form>

      {submittedUsers.length > 0 && (
        <div className="mt-8">
          <h2 className="text-2xl font-semibold mb-2">Submitted Users:</h2>
          <ul className="list-disc pl-6">
            {submittedUsers.map((user, index) => (
              <li key={index}>
                <span className="font-bold">Username:</span> {user.username},{" "}
                <span className="font-bold">Email:</span> {user.email}
              </li>
            ))}
          </ul>
        </div>
      )}
    </div>
  );
};

export default ValidForm;
