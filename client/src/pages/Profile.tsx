import { useParams } from "react-router-dom"

export default function Profile() {
  const { userId } = useParams<string>();

  return (
    <div>Profile</div>
  )
}