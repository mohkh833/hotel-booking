import React, { useEffect } from 'react'
import { useParams } from 'react-router-dom';
import ApiService from '../../service/ApiService';
import { useNavigate } from 'react-router-dom'

const EmailConfirmationPage = () => {
  const { confirmationToken } = useParams();

  const navigate = useNavigate();

  useEffect(() => {
    const confirmAccount = async () => {
      try {
        const response = await ApiService.confirmAccount(confirmationToken);
        console.log(response)
        if(response.statusCode === 200) {
          setTimeout(() => {
            navigate('/login')
          }, 5000)
        }
      } catch (error) {
        console.error('Error Confirmation:', error.message);
      }
    }
    confirmAccount()
  },[confirmationToken])

  return (
    <div> 
        <h1>
            Account is confirmed successfully
        </h1>
        
    </div>
  )
}

export default EmailConfirmationPage