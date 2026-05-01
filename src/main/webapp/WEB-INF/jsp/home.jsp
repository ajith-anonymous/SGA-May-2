<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Orders & Customers Management</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700;800&display=swap" rel="stylesheet">
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body {
            font-family: 'Inter', sans-serif;
            background: linear-gradient(135deg, #0f0c29 0%, #302b63 50%, #24243e 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            color: #fff;
        }
        .container {
            text-align: center;
            padding: 60px 40px;
        }
        .logo {
            font-size: 4rem;
            margin-bottom: 20px;
            animation: bounce 2s infinite;
        }
        @keyframes bounce {
            0%, 100% { transform: translateY(0); }
            50% { transform: translateY(-15px); }
        }
        h1 {
            font-size: 3rem;
            font-weight: 800;
            background: linear-gradient(135deg, #667eea, #764ba2, #f093fb);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            background-clip: text;
            margin-bottom: 12px;
        }
        .subtitle {
            font-size: 1.15rem;
            color: rgba(255,255,255,0.6);
            margin-bottom: 50px;
            font-weight: 300;
        }
        .cards {
            display: flex;
            gap: 30px;
            justify-content: center;
            flex-wrap: wrap;
        }
        .card {
            background: rgba(255,255,255,0.06);
            backdrop-filter: blur(20px);
            border: 1px solid rgba(255,255,255,0.1);
            border-radius: 20px;
            padding: 40px 35px;
            width: 280px;
            transition: all 0.4s cubic-bezier(0.175, 0.885, 0.32, 1.275);
            text-decoration: none;
            color: #fff;
        }
        .card:hover {
            transform: translateY(-10px) scale(1.03);
            border-color: rgba(255,255,255,0.3);
            box-shadow: 0 20px 60px rgba(102,126,234,0.3);
        }
        .card-icon {
            font-size: 3rem;
            margin-bottom: 18px;
        }
        .card h2 {
            font-size: 1.3rem;
            font-weight: 600;
            margin-bottom: 10px;
        }
        .card p {
            font-size: 0.9rem;
            color: rgba(255,255,255,0.5);
            line-height: 1.6;
        }
        .card.customers { border-top: 3px solid #667eea; }
        .card.orders { border-top: 3px solid #f093fb; }
        .card.join { border-top: 3px solid #4fd1c5; }
        footer {
            margin-top: 60px;
            color: rgba(255,255,255,0.3);
            font-size: 0.85rem;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="logo">🛒</div>
        <h1>Orders & Customers</h1>
        <p class="subtitle">Spring Boot JPA Management System</p>

        <div class="cards">
            <a href="${pageContext.request.contextPath}/customers/list" class="card customers">
                <div class="card-icon">👥</div>
                <h2>Customers</h2>
                <p>View, add, and update customer information</p>
            </a>

            <a href="${pageContext.request.contextPath}/orders/list" class="card orders">
                <div class="card-icon">📦</div>
                <h2>Orders</h2>
                <p>Manage orders with product details and statuses</p>
            </a>

            <a href="${pageContext.request.contextPath}/orders/list" class="card join">
                <div class="card-icon">🔗</div>
                <h2>Joined View</h2>
                <p>Orders with customer info via INNER JOIN query</p>
            </a>
        </div>

        <footer>
            &copy; 2026 Orders & Customers Management &mdash; Spring Boot + JPA + JSP
        </footer>
    </div>
</body>
</html>
