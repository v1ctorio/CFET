const env = process.env

const token = env.token
const email = env.email
const account_identifier = env.account_identifier

const options = {
    method: 'GET',
    headers: {'Content-Type': 'application/json', 'Authorization': `Bearer ${token}`}
  };

  fetch(`https://api.cloudflare.com/client/v4/accounts/${account_identifier}/email/routing/addresses`, options)
    .then(response => response.json())
    .then(response => console.log(response))
    .catch(err => console.error(err));