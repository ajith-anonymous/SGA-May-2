<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Customer</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap" rel="stylesheet">
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
            padding: 40px 20px;
        }
        .form-card {
            background: rgba(255,255,255,0.06);
            backdrop-filter: blur(20px);
            border: 1px solid rgba(255,255,255,0.1);
            border-radius: 24px;
            padding: 45px 40px;
            width: 100%;
            max-width: 520px;
            animation: fadeIn 0.5s ease;
        }
        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(20px); }
            to { opacity: 1; transform: translateY(0); }
        }
        .form-card h1 {
            font-size: 1.8rem;
            font-weight: 700;
            margin-bottom: 8px;
            background: linear-gradient(135deg, #667eea, #764ba2);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            background-clip: text;
        }
        .form-card .subtitle {
            color: rgba(255,255,255,0.5);
            font-size: 0.9rem;
            margin-bottom: 30px;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-group label {
            display: block;
            font-weight: 500;
            font-size: 0.9rem;
            margin-bottom: 8px;
            color: rgba(255,255,255,0.8);
        }
        .form-group input {
            width: 100%;
            padding: 12px 16px;
            background: rgba(255,255,255,0.08);
            border: 1px solid rgba(255,255,255,0.15);
            border-radius: 12px;
            color: #fff;
            font-size: 0.95rem;
            font-family: 'Inter', sans-serif;
            transition: all 0.3s ease;
            outline: none;
        }
        .form-group input:focus {
            border-color: #667eea;
            box-shadow: 0 0 0 3px rgba(102,126,234,0.2);
        }
        .form-group input::placeholder {
            color: rgba(255,255,255,0.3);
        }
        .error-text {
            color: #fc8181;
            font-size: 0.8rem;
            margin-top: 5px;
        }
        .alert {
            padding: 14px 20px;
            border-radius: 12px;
            margin-bottom: 20px;
            font-size: 0.9rem;
        }
        .alert-error {
            background: rgba(245,101,101,0.15);
            border: 1px solid rgba(245,101,101,0.3);
            color: #fc8181;
        }
        .btn-group {
            display: flex;
            gap: 12px;
            margin-top: 30px;
        }
        .btn {
            display: inline-flex;
            align-items: center;
            justify-content: center;
            gap: 8px;
            padding: 13px 28px;
            border-radius: 12px;
            font-weight: 600;
            font-size: 0.9rem;
            text-decoration: none;
            transition: all 0.3s ease;
            border: none;
            cursor: pointer;
            font-family: 'Inter', sans-serif;
            flex: 1;
        }
        .btn-primary {
            background: linear-gradient(135deg, #667eea, #764ba2);
            color: #fff;
        }
        .btn-primary:hover {
            transform: translateY(-2px);
            box-shadow: 0 8px 25px rgba(102,126,234,0.4);
        }
        .btn-secondary {
            background: rgba(255,255,255,0.1);
            color: #fff;
            border: 1px solid rgba(255,255,255,0.2);
        }
        .btn-secondary:hover {
            background: rgba(255,255,255,0.15);
            transform: translateY(-2px);
        }
    </style>
</head>
<body>
    <div class="form-card">
        <h1>➕ Add Customer</h1>
        <p class="subtitle">Fill in the details to create a new customer</p>

        <c:if test="${not empty errorMessage}">
            <div class="alert alert-error">❌ ${errorMessage}</div>
        </c:if>

        <form:form method="POST" action="${pageContext.request.contextPath}/customers/add" modelAttribute="customer">
            <div class="form-group">
                <label for="name">Full Name *</label>
                <form:input path="name" id="name" placeholder="Enter full name" />
                <form:errors path="name" cssClass="error-text" />
            </div>

            <div class="form-group">
                <label for="email">Email Address *</label>
                <form:input path="email" id="email" type="email" placeholder="Enter email address" />
                <form:errors path="email" cssClass="error-text" />
            </div>

            <div class="form-group">
                <label for="phone">Phone Number</label>
                <form:input path="phone" id="phone" placeholder="Enter phone number" />
                <form:errors path="phone" cssClass="error-text" />
            </div>

            <div class="form-group">
                <label for="address">Address</label>
                <form:input path="address" id="address" placeholder="Enter address" />
                <form:errors path="address" cssClass="error-text" />
            </div>

            <div class="btn-group">
                <a href="${pageContext.request.contextPath}/customers/list" class="btn btn-secondary">← Back</a>
                <button type="submit" class="btn btn-primary">Save Customer</button>
            </div>
        </form:form>
    </div>
</body>
</html>
