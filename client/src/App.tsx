import Home from "./pages/Home"
import Profile from "./pages/Profile"
import Activity from "./pages/Activity"
import Search from "./pages/Search"
import { Routes, Route } from "react-router-dom"
import Login from "./pages/Login"

export default function App() {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/login" element={<Login />} />
      <Route path="/search" element={<Search />} />
      <Route path="/activity" element={<Activity />} />
      <Route path="/:userId" element={<Profile />} />
    </Routes>
  )
}
