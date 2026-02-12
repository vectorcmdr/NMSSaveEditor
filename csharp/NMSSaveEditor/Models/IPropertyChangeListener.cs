namespace NMSSaveEditor.Models;

public interface IPropertyChangeListener
{
    void PropertyChanged(string path, object? oldValue, object? newValue);
}
