export const diffInDays = (dueDate: Date, paymentDate: Date): number => {
    if (!(dueDate instanceof Date) || isNaN(dueDate.getTime())) return 0;

    if (!(paymentDate instanceof Date) || isNaN(paymentDate.getTime())) return 0;

    const msPerDay = 1000 * 60 * 60 * 24; // dia em milissegundos
    const diff = paymentDate.getTime() - dueDate.getTime();

    if (diff <= 0) return 0;

    return Math.floor(diff / msPerDay);
};
